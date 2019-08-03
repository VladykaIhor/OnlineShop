package dao;

import model.Code;

import java.util.Optional;

public interface CodeDao {

    void addCode(Code code);

    Optional<Code> getCode(Long id);
}
