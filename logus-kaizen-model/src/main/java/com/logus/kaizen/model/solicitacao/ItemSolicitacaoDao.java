package com.logus.kaizen.model.solicitacao;

import java.util.Collection;

import com.logus.core.model.persistence.DataAccessObject;

public interface ItemSolicitacaoDao extends DataAccessObject<ItemSolicitacao>{
	Collection<ItemSolicitacao> loadItensSolicitacao();
}
