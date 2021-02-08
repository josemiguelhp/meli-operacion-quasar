package com.josehernandez.meli.utils;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MessageResolverImpl implements MessageResolver {

    @Override
    public String getMessage(String[]... messages) throws Exception {

        if (messages == null || messages.length == 0)
            throw new IllegalArgumentException("Se necesita al menos un mensaje para parsear!");

        List<String[]> messagesList = Arrays.asList(messages);
        int firstSize = messages[0].length;

        boolean allSameSize = messagesList.stream().allMatch(x -> x.length==firstSize);
        if(allSameSize){
            //caso feliz (sin desfasaje en ningun mensaje) todos tienen la misma longuitud
            return parseMessage(messages, firstSize);
        }else{
            //tama;o de mensaje con menos elementos (palabras)
            final AtomicInteger lastMinimumLength = new AtomicInteger(-1);
            messagesList.forEach((String[] message) ->{
                if(lastMinimumLength.get()==-1||message.length<lastMinimumLength.get())
                    lastMinimumLength.set(message.length);
            });
            //descubrir desfase arreglarlo y tratar de parsear
            return parseMessage(fixOutOfPhase(messagesList, lastMinimumLength.get()),lastMinimumLength.get());
        }
    }

    private String parseMessage(String[][] messages, int columnsLenght) throws Exception {
        StringBuilder result = new StringBuilder();
        /*
             |   0   |   1    |  2
           __|_______|________|__________   |
           0 |  "es" |   "    |  "          |
           ------------------------------   |   Analizamos asi
           1 |   "   |    "   | frase       |
           ------------------------------  \|/
           2 |  "es" | "una"  |  "          V ---------------->
           ------------------------------
           comparo todos los elementos de cada columna con las siguientes reglas:
           -elementos vacios se ignoran
           -elementos no vacios se comparan con los demas que no esten vacios, si es diferente a alguno se devuelve
            una excepcion puesto que los mensajes informados se contradicen y no es posible obtener el mensaje de la nave.
           -si la columna esta okay se concatena la palabra en el resultado final
         */
        for(int i = 0; i< columnsLenght; i++) {
            String lastColumnWord = "";
            String[] columnWords = new String[columnsLenght];
            for (String[] message : messages) {
                //recorriendo la columna
                String columnMessage = message[i];
                if (!columnMessage.isEmpty()) {//ignoramos columnas vacias
                    //comparamos con el resto de la columna que ya analizamos a ver existe alguna palabra diferente
                    for (String word : columnWords) {
                        if (word != null && !word.equals(columnMessage)) {
                            throw new Exception("no es posible determinar el mensaje");
                        }
                    }
                    columnWords[i] = columnMessage;
                    lastColumnWord = columnMessage;
                }
            }
            result.append(lastColumnWord).append(" ");
        }
        return result.toString().trim();
    }

    private String[][] fixOutOfPhase(List<String[]> messagesList, int minimumLength) {
        //buscamos el desfasaje de cada mensaje
        Map<Integer, Integer> messagesMap = new HashMap<>();
        for (int i = 0;i<messagesList.size();i++) {
            if(messagesList.get(i).length>minimumLength){
                messagesMap.put(i, messagesList.get(i).length-minimumLength);//segundo arg->desfasaje
            }
        }

        //"arreglamos" los mensajes desfasados
        String[][] result = new String[messagesList.size()][minimumLength];
        for(int i = 0; i< messagesList.size(); i++){
            if(messagesMap.containsKey(i)){
                String[] message = messagesList.get(i);
                int outOfPhase = messagesMap.get(i);
                List<String> nWords = Arrays.asList(message);
                List<String> filteredMessage=nWords.subList(outOfPhase,message.length);
                String[] filterMessageArr = new String[filteredMessage.size()];
                result[i] = filteredMessage.toArray(filterMessageArr);
            }else {
                result[i] = messagesList.get(i);
            }
        };

        return result;
    }
}
