package service.impl;

import dao.CodeDao;
import dao.impl.hib.CodeDaoHibImpl;
import model.Code;
import service.CodeService;

import java.util.Optional;

public class CodeServiceImpl implements CodeService {

    private static CodeDao codeDao = new CodeDaoHibImpl();

    @Override
    public void addCode(Code code) {
        codeDao.addCode(code);
    }

    @Override
    public Optional<Code> getCode(Long id) {
        return codeDao.getCode(id);
    }
}
