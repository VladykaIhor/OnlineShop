package mate.academy.service.impl;

import mate.academy.dao.CodeDao;
import mate.academy.dao.impl.jdbc.CodeDaoJDBCImpl;
import mate.academy.model.Code;
import mate.academy.service.CodeService;

public class CodeServiceImpl implements CodeService {

    private static CodeDao codeDao = new CodeDaoJDBCImpl();

    @Override
    public void addCode(Code code) {
        codeDao.addCode(code);
    }

    @Override
    public String getCode(Long id) {
        return codeDao.getCode(id);
    }
}
