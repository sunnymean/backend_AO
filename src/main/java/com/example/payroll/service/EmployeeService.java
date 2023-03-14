package com.example.payroll.service;

import com.example.payroll.model.Employee;
import com.example.payroll.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

  private final EmployeeRepository repository;

//  @Transactional(readOnly = true)
  public Employee getEmployeeById(Long id) {
//    if (ObjectUtils.isEmpty(email)) {
//      throw new CustomBusinessException(CommonMessageConstants.MSG_EMAIL_REQUIRED,
//          StatusCodeConstants.MANDATORY_PARAM_ERROR);
//    }

//    return memberRepository.selectMemberByEmailAddress(MemberDto.builder().email(email).build());
    return repository.selectEmployeeById(id);
  }

}
