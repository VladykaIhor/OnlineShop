package service;

import model.Code;

import java.util.Optional;

public interface CodeService {

    void addCode(Code code);

    Optional<Code> getCode(Long id);
}
