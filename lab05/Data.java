package lab05;

import java.text.*;
import java.util.*;

public class Data {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static Date stringToDate(String data) {
        try {
            return DATE_FORMAT.parse(data);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String dateToString(Date data) {
        return DATE_FORMAT.format(data);
    }

    public static int calcularIdade(Date dataNascimento) {
        Calendar nascimento = Calendar.getInstance();
        nascimento.setTime(dataNascimento);
        Calendar atual = Calendar.getInstance();

        int idade = atual.get(Calendar.YEAR) - nascimento.get(Calendar.YEAR);

        if (atual.get(Calendar.MONTH) < nascimento.get(Calendar.MONTH) ||
                (atual.get(Calendar.MONTH) == nascimento.get(Calendar.MONTH) &&
                        atual.get(Calendar.DAY_OF_MONTH) < nascimento.get(Calendar.DAY_OF_MONTH))) {
            idade--;
        }

        return idade;
    }
}

