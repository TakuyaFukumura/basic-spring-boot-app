package com.example.myapplication.service

import spock.lang.Specification

/**
 * AiServiceのSpockテスト
 * Spockの仕様記述形式でテストケースを記述する
 */
class AiServiceSpec extends Specification {

    def aiService = new AiService()

    def "APIキーが設定されていない場合、サンプル豆知識が返されること"() {
        given: "APIキーが設定されていないAiService"
        // デフォルトでAPIキーは空文字列

        when: "getTriviaを呼び出す"
        def result = aiService.getTrivia()

        then: "サンプル豆知識が返される"
        result != null
        !result.isEmpty()
        // サンプル豆知識のパターンをチェック
        (result.contains("タコには3つの心臓") ||
         result.contains("ハチミツは腐りません") ||
         result.contains("人間の脳は約1000億個") ||
         result.contains("キリンの舌は約50cm") ||
         result.contains("雷は太陽の表面温度"))
    }

    def "getTriviaは空でない文字列を返すこと"() {
        when: "getTriviaを呼び出す"
        def result = aiService.getTrivia()

        then: "空でない文字列が返される"
        result != null
        !result.trim().isEmpty()
    }

    def "複数回getTriviaを呼び出しても正常に動作すること"() {
        when: "getTriviaを複数回呼び出す"
        def results = []
        3.times {
            results << aiService.getTrivia()
        }

        then: "すべて空でない文字列が返される"
        results.every { it != null && !it.trim().isEmpty() }
        results.size() == 3
    }
}
