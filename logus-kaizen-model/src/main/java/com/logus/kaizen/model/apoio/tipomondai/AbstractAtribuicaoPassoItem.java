package com.logus.kaizen.model.apoio.tipomondai;

import com.logus.kaizen.model.apoio.processo.Passo;

public abstract class AbstractAtribuicaoPassoItem {

	private Passo passo;

	public abstract String getAtribuicao();

	public AbstractAtribuicaoPassoItem() {
	}

	public AbstractAtribuicaoPassoItem(Passo passo) {
		this.passo = passo;
	}

	public Passo getPasso() {
		return passo;
	}

	public void setPasso(Passo passo) {
		this.passo = passo;
	}

}
