package com.example.myapplication.service;

/**
 * AI機能サービスのインターフェース
 * Gemini APIを使用した豆知識取得機能を提供する
 */
public interface AiService {

    /**
     * Gemini APIを使用して豆知識を取得する
     *
     * @return 豆知識の文字列
     * @throws RuntimeException API呼び出しに失敗した場合
     */
    String getTrivia();
}
