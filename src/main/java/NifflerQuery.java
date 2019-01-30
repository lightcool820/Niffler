import org.apache.calcite.sql.SqlIdentifier;
import org.apache.calcite.sql.SqlSelect;

import java.util.List;
import java.util.Map;

/**
 * 描述:
 * 查询类
 *
 * @outhor admin
 * @create 2019-01-29 下午2:45
 */
public class NifflerQuery {

    Object dataSource;

    SqlSelect sqlSelect;

    public NifflerQuery(Object dataSource, SqlSelect sqlSelect) {

    }

    public NifflerQuery() {
    }

    /**
     * 处理select
     *
     * @return
     */
    public NifflerQuery select() {
        return this;
    }

    /**
     * 处理from
     *
     * @return
     */
    public NifflerQuery from() throws Exception {
        try {
            //如果数据源json是数组，那么from关键字无效
            if (dataSource instanceof List) {
                return this;
            }
            //from 的形式可以为 a.b.c
            if (!(sqlSelect.getFrom() instanceof SqlIdentifier)) {
                throw new Exception("from的形式不符合要求：不能嵌套select");
            }

            SqlIdentifier fromNode = (SqlIdentifier) sqlSelect.getFrom();
            //目前from只能支持一个属性
            if (fromNode.names.size() != 1) {
                throw new Exception("from的形式不符合要求：from只能有一个属性");
            }

            Map dataSourceMap = (Map) dataSource;
            String[] listFromKey = fromNode.names.get(0).split(".");

            //用于存储最终的from值
            Object fromValue = null;
            int index = 0;
            for (String fromKey : listFromKey) {
                if (fromValue == null) {
                    fromValue = dataSourceMap.get(fromKey);
                } else {
                    //如果from的a.b.c.d中间某一个属性不为map，而且不是最后一个节点，那么属于异常from节点
                    if (!(fromValue instanceof Map) && index != listFromKey.length - 1) {
                        throw new Exception("from的形式不符合要求：from多级节点中存在非Map节点");
                    }
                }

                index++;
            }

            return this;

        } catch (Exception ex) {
            System.out.println("****************** Exception for Analyze from ******************");
            System.out.println(ex.toString());
            throw ex;
        }


    }

    /**
     * 处理where
     *
     * @return
     */
    public NifflerQuery where() {
        return this;
    }

    /**
     * 处理group by
     *
     * @return
     */
    public NifflerQuery group() {
        return this;
    }

    /**
     * 处理having
     *
     * @return
     */
    public NifflerQuery having() {
        return this;
    }

    /**
     * 处理order
     *
     * @return
     */
    public NifflerQuery order() {
        return this;
    }

    /**
     * 处理limit
     *
     * @return
     */
    public NifflerQuery limit() {
        return this;
    }
}