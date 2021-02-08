package com.josehernandez.meli.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MessageResolverTests {

    private MessageResolver messageResolver;

    @BeforeEach
    void initUseCase() {
        messageResolver = new MessageResolverImpl();
    }

    @Test
    void getMessageWithFixedElements() {
        String message = null;
        try {
            String[] kenobiMessages = new String[]{"este", "", "un", "mensaje"};
            String[] skywalkerMessages = new String[]{"", "", "un", ""};
            String[] satoMessages = new String[]{"este", "es", "", "mensaje"};
            message = messageResolver.getMessage(kenobiMessages, skywalkerMessages, satoMessages);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert message != null;
        Assertions.assertThat(message).isEqualTo("este es un mensaje");
    }

    @Test
    void getMessageWithABunchOfFixedElements() {
        String message = null;
        try {
            String[] mA = new String[]{"anita", "", "la", "tina"};
            String[] mB = new String[]{"", "", "", "tina"};
            String[] mC = new String[]{"", "lava", "", ""};
            String[] mD = new String[]{"", "", "", ""};
            String[] mE = new String[]{"anita", "lava", "la", "tina"};
            String[] mF = new String[]{"", "", "", ""};
            String[] mG = new String[]{"", "lava", "", ""};

            message = messageResolver.getMessage(mA, mB, mC, mD, mE, mF, mG);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert message != null;
        Assertions.assertThat(message).isEqualTo("anita lava la tina");
    }

    @Test
    void getErrorWithFixedElements() {
        //en este caso debe dar error porque los mensajes algunos son distintos por lo que no se puede predecir con exactitud el resultado
        String[] kenobiMessages = new String[]{"este", "", "loco", "mensaje"};
        String[] skywalkerMessages = new String[]{"", "", "un", ""};
        String[] satoMessages = new String[]{"era", "es", "", "mensaje"};
        Assertions.assertThatThrownBy(() -> messageResolver.getMessage(kenobiMessages, skywalkerMessages, satoMessages))
                .isInstanceOf(Exception.class);
    }

    @Test
    void getMessageWithOutOfPhaseElements() {
        String message = null;
        try {
            String[] kenobiMessages = new String[]{"", "este", "es", "un", "mensaje"};
            String[] skywalkerMessages = new String[]{"este", "", "un", "mensaje"};
            String[] satoMessages = new String[]{"", "", "es", "", "mensaje"};
            message = messageResolver.getMessage(kenobiMessages, skywalkerMessages, satoMessages);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert message != null;
        Assertions.assertThat(message).isEqualTo("este es un mensaje");
    }

    @Test
    void getMessageWithABunchOfOutOfPhaseElements() {
        String message = null;
        try {
            String[] mA = new String[]{"","","","","anita", "", "la", "tina"};
            String[] mB = new String[]{"","","","","","", "", "", "tina"};
            String[] mC = new String[]{"","","","", "lava", "", ""};
            String[] mD = new String[]{"","","","","", "", "", ""};
            String[] mE = new String[]{"anita", "lava", "", "tina"};
            String[] mF = new String[]{"","","","","","","","","", "", "", ""};
            String[] mG = new String[]{"","","","","","","","","", "lava", "", ""};
            message = messageResolver.getMessage(mA, mB, mC, mD, mE, mF, mG);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert message != null;
        Assertions.assertThat(message).isEqualTo("anita lava la tina");
    }
}
