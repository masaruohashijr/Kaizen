package com.logus.kaizen.model.util;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PreUpdate;

import com.logus.core.model.aut.login.LoginManager;
import com.logus.core.model.persistence.DataAccessObject;
import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.auditoria.GrupoMudanca;
import com.logus.kaizen.model.auditoria.ItemMudanca;

/**
 *
 * @author Roberto Araújo
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class YokaiListener {

	private Object oldObject;

	@PostPersist
	public void onInsert(Object object) {
		System.out.println("INSERT" + "-" + object.toString());
	}

	@PreUpdate
	public void beforeUpdate(Object object) {
		preencheOldObject(object);
	}

	private void preencheOldObject(Object object) {
		Field[] declaredFields = object.getClass().getDeclaredFields();
		Long id;
		for (Field field : declaredFields) {
			field.setAccessible(true);
			if (field.getName() == "id") {
				try {
					id = (Long) field.get(object);
					DataAccessObject<?> dao = ApoioDataService.get().getDao(object);
					if(null != dao) {
						oldObject = dao.load(id);
					}
					break;
				} catch (IllegalArgumentException | IllegalAccessException e) {
				}
			}
		}
	}

	@PostUpdate
	public void onUpdate(Object newObject) throws IllegalArgumentException, IllegalAccessException {
		if(null == oldObject) return;
		Map<String, Object> mapaDeCampos = new HashMap<>();
		Field[] declaredFields = newObject.getClass().getDeclaredFields();
		for (Field oldField : declaredFields) {
			oldField.setAccessible(true);
			Object oldValue = oldField.get(oldObject);
			mapaDeCampos.put(oldField.getName(), oldValue);
			System.out.println("Nome do Campo: " + oldField.getName() + " Valor do Campo: " + oldValue);
		}
		GrupoMudanca grupoMudanca = null;
		System.out.println("NOVO VALOR");
		for (Field newField : declaredFields) {
			newField.setAccessible(true);
			Object newValue = newField.get(newObject);
			System.out.println("Nome do Campo: " + newField.getName() + " Valor do Campo: " + newValue);
			Object oldValue = mapaDeCampos.get(newField.getName());
			if (null != oldValue && !"".equals(newValue) && !newValue.equals(oldValue)) {
				String codigoAudor = "";
				Date dataMudanca = null;
				String nomeEntidade = "";
				Long idEntidade = null;
				if (grupoMudanca == null) {
					codigoAudor = LoginManager.getAccessControl().getUser().getCodigo();
					dataMudanca = new Date(System.currentTimeMillis());
					nomeEntidade = newObject.getClass().getTypeName();
					grupoMudanca = new GrupoMudanca();
					grupoMudanca.setAtivo(true);
					grupoMudanca.setAutor(codigoAudor);
					grupoMudanca.setDataMudanca(dataMudanca);
					grupoMudanca.setEntidade(nomeEntidade);
					for (Field field : declaredFields) {
						field.setAccessible(true);
						if (field.getName() == "id") {
							idEntidade = (Long) field.get(newObject);
							break;
						}
					}
					grupoMudanca.setIdEntidade(idEntidade);
				}
				ItemMudanca itemMudanca = new ItemMudanca();
				itemMudanca.setAtivo(true);
				if(null == codigoAudor) {
					codigoAudor = LoginManager.getAccessControl().getUser().getCodigo();
				}
				itemMudanca.setAutor(codigoAudor);
				if(null == dataMudanca) {
					dataMudanca = new Date(System.currentTimeMillis());
				}
				itemMudanca.setDataMudanca(dataMudanca);
				if(null == nomeEntidade) {
					nomeEntidade = newObject.getClass().getTypeName();
				}
				itemMudanca.setEntidade(nomeEntidade);
				itemMudanca.setIdEntidade(idEntidade);
				itemMudanca.setNomeCampo(newField.getName());
				itemMudanca.setValorAntigo(String.valueOf(oldValue));
				itemMudanca.setValorNovo(String.valueOf(newValue));
				itemMudanca.setGrupo(grupoMudanca);
				grupoMudanca.getItensMudanca().add(itemMudanca);
				System.out.println("Houve uma mudança do valor: " + oldValue + " Para o valor: " + newValue);
			} else {
				System.out.println("Não houve mudança.");
			}
		}
		if (null != grupoMudanca) {
			ApoioDataService.get().getGrupoMudancaDao().insert(grupoMudanca);
		}
	}

	@PostRemove
	public void onDelete(Object object) {
		System.out.println("DELETE" + "-" + object.toString());
	}

	@PostLoad
	public void onLoad(Object object) {
		System.out.println("LOAD" + '-' + object.toString());
	}
}
