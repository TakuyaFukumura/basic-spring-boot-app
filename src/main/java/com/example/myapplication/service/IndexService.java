package com.example.myapplication.service;

/**
 * インデックスサービスのインターフェース
 * メッセージ取得機能を提供する
 */
public interface IndexService {

    /**
     * データベースからメッセージを取得する
     *
     * @return メッセージ文字列、取得できない場合は "Error!"
     */
    String getMessage();
}
