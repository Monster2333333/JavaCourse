package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.index.impl.TermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.parse.AbstractTermTupleFilter;
import hust.cs.javacourse.search.util.Config;
import hust.cs.javacourse.search.util.StopWords;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * <pre>
 *     StopWordTermTupleFilter继承于AbstractTermTupleFilter抽象父类，读取一个AbstractTermTupleStream流对象，
 *     根据停用词表过滤，保留不在停用词表中的Term，停用词表由util包的StopWords.STOP_WORDS指定
 * </pre>
 */
public class StopWordTermTupleFilter extends AbstractTermTupleFilter {
    /**
     * 构造函数
     *
     * @param input 输入流，AbstractTermTupleStream子类对象
     */
    public StopWordTermTupleFilter(AbstractTermTupleStream input) {
        super(input);
    }

    /**
     * 返回下一个经过过滤后的三元组
     *
     * @return 三元组
     */
    public AbstractTermTuple next() {
        if (this.input == null) return null;
        AbstractTermTuple termTuple = new TermTuple();
        do {
            termTuple = this.input.next();
            if (termTuple == null) return null;
        } while (Arrays.binarySearch(StopWords.STOP_WORDS, termTuple.term.getContent()) >= 0);
        return termTuple;
    }
}
