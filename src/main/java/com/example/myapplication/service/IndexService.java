package com.example.myapplication.service;

/**
 * インデックスサービスのインターフェース
 */
public interface IndexService {

    /**
     * データベースからメッセージを取得する
     *
     * @return メッセージ文字列、取得できない場合は "Error!"
     */
    String getMessage();
}
