package analiz;

import java.util.ArrayList;
import java.util.List;

public class Analizador {
    public int[] acha_sequencia(double[] probs, int[] valores) {
        int n = probs.length;
        List<Integer> sequencia = new ArrayList<>();
        boolean[] selecionado = new boolean[n];

        for (int i = 0; i < n; i++) {
            int melhorPergunta = -1;
            double melhorRecompensa = 0.0;

            for (int j = 0; j < n; j++) {
                if (!selecionado[j]) {
                    double recompensaEsperada = calcularRecompensaEsperada(probs, valores, selecionado, j);
                    if (recompensaEsperada > melhorRecompensa) {
                        melhorRecompensa = recompensaEsperada;
                        melhorPergunta = j;
                    }
                }
            }

            sequencia.add(melhorPergunta);
            selecionado[melhorPergunta] = true;
        }

        int[] sequenciaArray = new int[sequencia.size()];
        for (int i = 0; i < sequencia.size(); i++) {
            sequenciaArray[i] = sequencia.get(i);
        }

        return sequenciaArray;
    }

    private double calcularRecompensaEsperada(double[] probs, int[] valores, boolean[] selecionado, int proximaPergunta) {
        double recompensaEsperada = 0.0;
        double probabilidadeAcumulada = 1.0;

        for (int i = 0; i < probs.length; i++) {
            if (selecionado[i] || i == proximaPergunta) {
                probabilidadeAcumulada *= probs[i];
                recompensaEsperada += probabilidadeAcumulada * valores[i];
            } else {
                probabilidadeAcumulada *= (1 - probs[i]);
            }
        }

        return recompensaEsperada;
    }
}


