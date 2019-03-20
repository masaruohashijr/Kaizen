package com.logus.kaizen.model.apoio.projeto;

import java.util.Collection;

public interface ItemPapelDao {

	Collection<ItemPapel> loadPapeisDoProjetoDoUsuario(String codigoUsuario);

}
