# e-Post Service
1. 대한민국 [공공데이터포털](https://www.data.go.kr/)의 Open API를 이용한 우편번호 검색 서비스 입니다.
2. 해당 서비스에서 사용된 Open API는 [링크](https://www.data.go.kr/dataset/15005912/openapi.do)에서 **우편번호 정보조회** 섹션을 참고하십시오.
3. 해당 섹션에서 **활용신청**을 통하여 **service key**를 발급받을 수 있습니다.
4. 해당 서비스를 사용하기 위해서는 [application.yaml](src/main/resources/application.yaml) 파일에서 **e-post.service-key** 항목에 3번 과정에서 얻어낸 service key를 입력하여야 합니다.
```yaml
e-post:
  service-key: 'input your e-post service key...'
```