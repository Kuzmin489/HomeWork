package io.fourfinanceit.backend.dto;


import java.util.List;

public class LoanFullDTO extends LoanDTO {

    private List<ExtensionDTO> extenstionList;

    public LoanFullDTO() {
    }

    public LoanFullDTO(LoanDTO loanDTO, List<ExtensionDTO> extenstionList) {
        super(loanDTO.getId(), loanDTO.getAmount(), loanDTO.getEndTerm());
        this.extenstionList = extenstionList;
    }

    public List<ExtensionDTO> getExtenstionList() {
        return extenstionList;
    }

    public LoanFullDTO setExtenstionList(List<ExtensionDTO> extenstionList) {
        this.extenstionList = extenstionList;
        return this;
    }
}
