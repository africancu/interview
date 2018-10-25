package cn.xzt.interview.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @Description:
 * @Auther: 周明军
 * @Date: 2018/10/23 15:19
 */
@Data
public class ProhibitWord{

    private Integer wordId;

    private String word;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ProhibitWord that = (ProhibitWord) object;
        return Objects.equals(word, that.word);
    }

    @Override
    public int hashCode() {

        return Objects.hash(word);
    }
}