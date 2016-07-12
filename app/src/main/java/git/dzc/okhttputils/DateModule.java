package git.dzc.okhttputils;

/**
 * Created by zajo on 15/12/10.
 */
public class DateModule {
    /**
     * success : 1
     * result : {"timestamp":"1449734277","m_text":"2015-12-10 15:57:57","datetime_2":"2015年12月10日 15时57分57秒","week_1":"4","week_2":"星期四","week_3":"周四","week_4":"Wednesday"}
     */

    private String success;
    /**
     * timestamp : 1449734277
     * m_text : 2015-12-10 15:57:57
     * datetime_2 : 2015年12月10日 15时57分57秒
     * week_1 : 4
     * week_2 : 星期四
     * week_3 : 周四
     * week_4 : Wednesday
     */

    private ResultEntity results;

    public void setSuccess(String success) {
        this.success = success;
    }

    public void setResult(ResultEntity result) {
        this.results = result;
    }

    public String getSuccess() {
        return success;
    }

    public ResultEntity getResult() {
        return results;
    }

    public static class ResultEntity {
        private String m_text;


        public void setTimestamp(String m_text) {
            this.m_text = m_text;
        }
        public String getTimestamp() {
            return m_text;
        }


    }
}
