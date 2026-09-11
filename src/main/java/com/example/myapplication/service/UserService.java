package com.example.myapplication.service;

import com.example.myapplication.dto.UserRegistrationDto;
import com.example.myapplication.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * ユーザーサービスのインターフェース
 */
public interface UserService extends UserDetailsService {

    /**
     * 新規ユーザーを作成する
     *
     * @param registrationDto 登録フォームからのデータ
     * @return 作成されたユーザーエンティティ
     */
    User createUser(UserRegistrationDto registrationDto);

    /**
     * ユーザー名の存在チェック
     *
     * @param username チェックするユーザー名
     * @return ユーザー名が既に存在する場合true
     */
    boolean isUsernameExists(String username);
}
