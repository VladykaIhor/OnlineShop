package mate.academy.dao;

import mate.academy.model.Code;

public interface CodeDao {

    void addCode(Code code);

    String getCode(Long id);
}
