package vn.edu.stu.WebBlogNauAn.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.stu.WebBlogNauAn.dto.RegisterDto;
import vn.edu.stu.WebBlogNauAn.model.Account;

@Component
public class AccountMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public AccountMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Account toAccount(RegisterDto registerDto) {
        return modelMapper.map(registerDto, Account.class);
    }
    public RegisterDto toDto(Account account) {
        return modelMapper.map(account, RegisterDto.class);
    }
}
