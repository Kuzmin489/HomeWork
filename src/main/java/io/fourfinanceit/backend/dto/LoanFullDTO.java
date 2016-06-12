package io.fourfinanceit.backend.dto;


import java.math.BigDecimal;
import java.util.List;

public class LoanFullDTO extends LoanDTO {

    private List<ExtenstionDTO> extenstionList;

    public LoanFullDTO() {
    }

    public LoanFullDTO(LoanDTO loanDTO, List<ExtenstionDTO> extenstionList) {
        super(loanDTO.getId(), loanDTO.getAmount(), loanDTO.getTerm());
        this.extenstionList = extenstionList;
    }

    public List<ExtenstionDTO> getExtenstionList() {
        return extenstionList;
    }

    public LoanFullDTO setExtenstionList(List<ExtenstionDTO> extenstionList) {
        this.extenstionList = extenstionList;
        return this;
    }
}
