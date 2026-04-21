# M3U8 Player Android

App Android simples para reproduzir arquivos .m3u8 locais ou de links.

## Funcionalidades
1. Reproduzir .m3u8 de URL da internet
2. Abrir arquivo .m3u8 local do celular usando Storage Access Framework
3. Player com controles usando ExoPlayer/Media3

## Como compilar
1. Abra a pasta no Android Studio
2. Aguarde o Gradle sincronizar
3. Build > Build APK(s)
4. APK estará em app/build/outputs/apk/debug/app-debug.apk

## Observações
- Para Android 13+ não precisa de permissão READ_EXTERNAL_STORAGE
- O app pede permissão persistente ao abrir arquivos locais
- Funciona com .m3u8 que apontam pra segmentos .ts online ou locais na mesma pasta

## Teste rápido
Link m3u8 de teste: https://devstreaming-cdn.apple.com/videos/streaming/examples/bipbop_4x3/bipbop_4x3_variant.m3u8
