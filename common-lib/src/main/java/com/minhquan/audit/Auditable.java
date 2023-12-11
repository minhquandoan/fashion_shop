package com.minhquan.audit;

import com.minhquan.model.SqlModel;

public interface Auditable {
    SqlModel getAudit();
    void setAudit(SqlModel model);
}
