package com.logus.kaizen.model.util;

public class GeradorInsertsFuncionalidadesUsuario {
/**
 *
 * @author Masaru Ohashi Júnior
 * @version 1.0.0
 * @since 1 de mar de 2019
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//="INSERT INTO security.seg_funcionalidade(
//		cod_funcionalidade, dsc_funcionalidade, cod_modulo, flg_only_cert)
//		VALUES ('"&A1&"', '"&SE(DIREITA(A1;9)="CADASTRAR";"Cadastrar ";"Consultar")&PRI.MAIÚSCULA(ESQUERDA(SUBSTITUIR(EXT.TEXTO(A1;5;NÚM.CARACT(A1));"_";" ");NÚM.CARACT(A1)-14))&"', 'DIVIDA',0);"
	}

}
