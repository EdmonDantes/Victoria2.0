package ru.liveproduction.victoria;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.liveproduction.victoria.core.entity.difficult.ICompareDifficult;
import ru.liveproduction.victoria.core.entity.questions.impl.Question;
import ru.liveproduction.victoria.core.exception.VictoriaException;

import java.util.Locale;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test1 {

    @Autowired
    @Qualifier("EQUALS")
    public ICompareDifficult difficult;

    @Autowired
    public Map<String,? extends ICompareDifficult> difficults;

    @Test
    public void test() {
        Assert.assertTrue(difficult.checkStrings("1", "1"));
        Assert.assertFalse(difficult.checkStrings("1", "2"));

        Question question = new Question();
        question.setId(12);

        try {
            question.checkAnswer(, difficult, "12314");
            Assert.fail();
        } catch (VictoriaException ignore) {}

        for (Map.Entry<String, ? extends ICompareDifficult> stringEntry : difficults.entrySet()) {
            Assert.assertEquals(stringEntry.getKey(), stringEntry.getValue().getTag());
        }
    }
}
