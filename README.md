# HarvestWatermelon（スイカ採り）
学生の頃cocos2dxで作ったアプリをDagger-Hiltなどの勉強のためにnative(kotlin)で作ってみました。使用については少し変更を加えております。
https://github.com/yuki14/harvest-watermelon
スイカ採り。棒人間をスワイプしてスイカを収穫するゲームです。採るスイカによってポイントがことなります。

<table>
<thead>
<tr>
<th>Top</th>
<th>Game Rule</th>
<th>Game</th>
</tr>
</thead>
<tbody>
<tr>
<td><a target="_blank" rel="noopener noreferrer" href="https://user-images.githubusercontent.com/37768294/144711321-f77fb18f-c08f-4278-8112-54ff8ca52e3a.png"><img src="https://user-images.githubusercontent.com/37768294/144711321-f77fb18f-c08f-4278-8112-54ff8ca52e3a.png" width="300" style="max-width: 100%;"></a></td>
<td><a target="_blank" rel="noopener noreferrer" href="https://user-images.githubusercontent.com/37768294/144712153-758f908e-8339-4af9-8065-703835c5d852.png"><img src="https://user-images.githubusercontent.com/37768294/144712153-758f908e-8339-4af9-8065-703835c5d852.png" width="300" style="max-width: 100%;"></a></td>
<td><a target="_blank" rel="noopener noreferrer" href="https://user-images.githubusercontent.com/37768294/144711724-b9a33044-493e-4160-822a-c3c64e76f707.png"><img src="https://user-images.githubusercontent.com/37768294/144711724-b9a33044-493e-4160-822a-c3c64e76f707.png" width="300" style="max-width: 100%;"></a></td>
</tr>
</tbody>
</table>

<table>
<thead>
<tr>
<th>Result</th>
<th>Result(スコア有りでランキング確認押下時)</th>
<th>Result(スコア無しでランキング確認押下時)</th>
</tr>
</thead>
<tbody>
<tr>
<td><a target="_blank" rel="noopener noreferrer" href="https://user-images.githubusercontent.com/37768294/144711805-dd198f4b-ce76-44f4-97e5-d879192fb90f.png"><img src="https://user-images.githubusercontent.com/37768294/144711805-dd198f4b-ce76-44f4-97e5-d879192fb90f.png" width="300" style="max-width: 100%;"></a></td>
<td><a target="_blank" rel="noopener noreferrer" href="https://user-images.githubusercontent.com/37768294/144712069-4f240d20-0714-41ba-b3ac-5188b9dd6979.png"><img src="https://user-images.githubusercontent.com/37768294/144712069-4f240d20-0714-41ba-b3ac-5188b9dd6979.png" width="300" style="max-width: 100%;"></a></td>
<td><a target="_blank" rel="noopener noreferrer" href="https://user-images.githubusercontent.com/37768294/144712326-b75fe86c-44c9-4fd5-850b-70e306c4175c.png"><img src="https://user-images.githubusercontent.com/37768294/144712326-b75fe86c-44c9-4fd5-850b-70e306c4175c.png" width="300" style="max-width: 100%;"></a></td>
</tr>
</tbody>
</table>

## アプリ構成 & 使用技術
- アプリ構成
    - マルチモジュール
    - MVVM + AAC
- 使用技術
    - Kotlin
    - Coroutines
    - AAC
      - Navigation Component
      - LiveData
      - ViewModel
      - ViewBinding
      - DataBinding
      - Room
    - Dependency Injection
      - Dagger-Hilt
      - Hilt-ViewModel
    - Moshi
    - Coil
