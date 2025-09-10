package com.example.myapplication.service;

import com.example.myapplication.dto.UserRegistrationDto;
import com.example.myapplication.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * ユーザーサービスのインターフェース
 * Spring SecurityのUserDetailsServiceを継承し、
 * データベースベースのユーザー認証とユーザー登録機能を提供する
 */
public interface UserServiceInterface extends UserDetailsService {

    /**
     * 新規ユーザーを作成する
     *
     * @param registrationDto 登録フォームからのデータ
     * @return 作成されたユーザーエンティティ
     * @throws IllegalArgumentException ユーザー名が既に存在する場合
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
