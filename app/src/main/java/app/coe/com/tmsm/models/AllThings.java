
package app.coe.com.tmsm.models;


import java.util.List;

public  class AllThings {


    /**
     * count : 2
     * result : [{"esp_id":"0001","esp_mac":"5E:CF:7F:5A:42:AC","esp_ip":"192.168.4.1","esp_name":"esp_tmsm_1","esp_time":"2019-02-01 20:54:38"},{"esp_id":"0002","esp_mac":"62:01:94:51:E0:B0","esp_ip":"192.168.4.1","esp_name":"esp_tmsm_2","esp_time":"2019-02-01 21:07:02"}]
     */

    private int count;
    private List<ResultBean> result;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * esp_id : 0001
         * esp_mac : 5E:CF:7F:5A:42:AC
         * esp_ip : 192.168.4.1
         * esp_name : esp_tmsm_1
         * esp_time : 2019-02-01 20:54:38
         */

        private String esp_id;
        private String esp_mac;
        private String esp_ip;
        private String esp_name;
        private String esp_time;

        public String getEsp_id() {
            return esp_id;
        }

        public void setEsp_id(String esp_id) {
            this.esp_id = esp_id;
        }

        public String getEsp_mac() {
            return esp_mac;
        }

        public void setEsp_mac(String esp_mac) {
            this.esp_mac = esp_mac;
        }

        public String getEsp_ip() {
            return esp_ip;
        }

        public void setEsp_ip(String esp_ip) {
            this.esp_ip = esp_ip;
        }

        public String getEsp_name() {
            return esp_name;
        }

        public void setEsp_name(String esp_name) {
            this.esp_name = esp_name;
        }

        public String getEsp_time() {
            return esp_time;
        }

        public void setEsp_time(String esp_time) {
            this.esp_time = esp_time;
        }
    }
}
