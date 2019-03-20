package com.logus.kaizen.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.logus.common.base.Strings;
import com.logus.core.model.persistence.Assignable;

@MappedSuperclass
public abstract class AbstractKaizenEntity implements Assignable<AbstractKaizenEntity>{

	private long id;

	@Column(name = "flg_ativo", nullable = false)
	private boolean ativo = Boolean.TRUE;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		if (Strings.isEmpty(id)) {
			return super.hashCode();
		}
		return Objects.hashCode(id);
	}

	public boolean equals(AbstractKaizenEntity obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AbstractKaizenEntity)) {
			return false;
		}
		return Objects.equals(this.id, obj.id);
	}



}
