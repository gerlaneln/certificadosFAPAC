package br.acre.fapac.certificado.gerar;

import java.util.Random;

public class gerarTokens {

	public static String criaTokens() {

		String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String palavra = "";

		Random random = new Random();

		int index = -1;
		{
			for (int i = 0; i < 10; i++) {
				String armazenaChaves = "";
				Random random1 = new Random();
				int x = random1.nextInt(10);
				String y = Integer.toString(x);
				palavra = palavra + y;
				index = random.nextInt(letras.length());
				armazenaChaves = letras.substring(index, index + 1);
				palavra = palavra + armazenaChaves;
			}

			String sempontos = palavra;

			StringBuilder stringBuilder = new StringBuilder(palavra);
			stringBuilder.insert(palavra.length() - 16, '.');
			stringBuilder.insert(palavra.length() - 11, '.');
			stringBuilder.insert(palavra.length() - 6, '.');
			stringBuilder.insert(palavra.length() - 1, '.');

			String pontos = stringBuilder.toString();
			return pontos;
	

		}

	}

	public class gerarTokensSem {

		public String criaTokensSem() {

			String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			String palavra = "";

			Random random = new Random();

			int index = -1;
			{
				for (int i = 0; i < 10; i++) {
					String armazenaChaves = "";
					Random random1 = new Random();
					int x = random1.nextInt(10);
					String y = Integer.toString(x);
					palavra = palavra + y;
					index = random.nextInt(letras.length());
					armazenaChaves = letras.substring(index, index + 1);
					palavra = palavra + armazenaChaves;
				}

				String sempontos = palavra;

				StringBuilder stringBuilder = new StringBuilder(palavra);
				stringBuilder.insert(palavra.length() - 16, '.');
				stringBuilder.insert(palavra.length() - 11, '.');
				stringBuilder.insert(palavra.length() - 6, '.');
				stringBuilder.insert(palavra.length() - 1, '.');

				String pontos = stringBuilder.toString();
				return sempontos;

			}

		}

	}
}
