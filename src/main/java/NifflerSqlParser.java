import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.SqlSelect;
import org.apache.calcite.sql.parser.SqlParser;

/**
 * 描述:
 * sql解析类
 *
 * @outhor admin
 * @create 2019-01-29 下午2:43
 */
public class NifflerSqlParser {
    String sql;

    public NifflerSqlParser() {

    }

    public NifflerSqlParser(String sql) {
        this.sql = sql;
    }

    public void parserSql() {
        try {
            SqlParser sqlParser = SqlParser.create(sql);
            SqlNode sqlNode = sqlParser.parseQuery();
            SqlSelect sqlSelect =  (SqlSelect)sqlNode;
            sqlSelect.getSelectList();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public static void main(String[] args) {
        String sql = "select a.b.c.d as fdsa,age,salary from xxxxxx where user.id = 1 and age > 30";
        NifflerSqlParser sqlParser = new NifflerSqlParser(sql);
        sqlParser.parserSql();
    }
}