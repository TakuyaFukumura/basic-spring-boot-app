package com.example.myapplication.service;

/**
 * AI機能サービスのインターフェース
 */
public interface AiService {

    /**
     * Gemini APIを使用して豆知識を取得する
     *
     * @return 豆知識の文字列
     */
    String getTrivia();
}
