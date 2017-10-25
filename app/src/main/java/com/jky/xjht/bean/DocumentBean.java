package com.jky.xjht.bean;

import java.util.List;

/**
 * Created by lenovo on 2017/10/13.
 */

public class DocumentBean {


    /**
     * Code : 0
     * Data : [{"name":"11","list":[{"ID":"e0046d01-14db-4e7d-8c80-3b7cb278856a","DocType":11,"ProjectID":"05f3a6c3-6bd1-4c6e-9ef7-f6c57ec39836","DocName":"技术支持1","DocPath":"Resource/DocFile/2017_10/2017101614303581.docx","RoleID":3,"UserID":"05f3a6c3-6bd1-4c6e-9ef7-f6c57ec39836","BuildDate":"2017-10-16T14:30:37.133"}]},{"name":"14","list":[{"ID":"743baf53-2a8c-4f99-afa3-23868e6cb35d","DocType":14,"ProjectID":"05f3a6c3-6bd1-4c6e-9ef7-f6c57ec39836","DocName":"组织设计1","DocPath":"Resource/DocFile/2017_10/2017101614310865.docx","RoleID":3,"UserID":"05f3a6c3-6bd1-4c6e-9ef7-f6c57ec39836","BuildDate":"2017-10-16 14:31:11"}]}]
     * Msg : 成功
     */

    private int Code;
    private String Msg;
    private List<DataBean> Data;

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * name : 11
         * list : [{"ID":"e0046d01-14db-4e7d-8c80-3b7cb278856a","DocType":11,"ProjectID":"05f3a6c3-6bd1-4c6e-9ef7-f6c57ec39836","DocName":"技术支持1","DocPath":"Resource/DocFile/2017_10/2017101614303581.docx","RoleID":3,"UserID":"05f3a6c3-6bd1-4c6e-9ef7-f6c57ec39836","BuildDate":"2017-10-16T14:30:37.133"}]
         */

        private String name;
        private List<ListBean> list;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * ID : e0046d01-14db-4e7d-8c80-3b7cb278856a
             * DocType : 11
             * ProjectID : 05f3a6c3-6bd1-4c6e-9ef7-f6c57ec39836
             * DocName : 技术支持1
             * DocPath : Resource/DocFile/2017_10/2017101614303581.docx
             * RoleID : 3
             * UserID : 05f3a6c3-6bd1-4c6e-9ef7-f6c57ec39836
             * BuildDate : 2017-10-16T14:30:37.133
             */

            private String ID;
            private int DocType;
            private String ProjectID;
            private String DocName;
            private String DocPath;
            private int RoleID;
            private String UserID;
            private String BuildDate;

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public int getDocType() {
                return DocType;
            }

            public void setDocType(int DocType) {
                this.DocType = DocType;
            }

            public String getProjectID() {
                return ProjectID;
            }

            public void setProjectID(String ProjectID) {
                this.ProjectID = ProjectID;
            }

            public String getDocName() {
                return DocName;
            }

            public void setDocName(String DocName) {
                this.DocName = DocName;
            }

            public String getDocPath() {
                return DocPath;
            }

            public void setDocPath(String DocPath) {
                this.DocPath = DocPath;
            }

            public int getRoleID() {
                return RoleID;
            }

            public void setRoleID(int RoleID) {
                this.RoleID = RoleID;
            }

            public String getUserID() {
                return UserID;
            }

            public void setUserID(String UserID) {
                this.UserID = UserID;
            }

            public String getBuildDate() {
                return BuildDate;
            }

            public void setBuildDate(String BuildDate) {
                this.BuildDate = BuildDate;
            }
        }
    }
}
