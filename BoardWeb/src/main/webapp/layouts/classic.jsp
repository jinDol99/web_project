<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!-- http://localhost/BoardWeb/myapp.homepage.tiles -->
<!-- 위 링크를 통해 apache tiles로 맨들어진 기본 layout을 확인할 수 있음.  -->
<!-- apache tiles는 페이지마다 중복되는 header, footer 등의 파트를 자동으로 관리해주는 라이브러리임. -->

<!-- webapp\layouts\ 안에있는 이 classic.jsp는 webapp\tiles\ 내부의 4개의 jsp를 가져와 구현함. -->
<!-- 자세한 내용은 webapp\WEB-INF\의 tiles.xml을 참고하거나 https://tiles.apache.org/framework/tutorial/index.html 홈페이지를 참고할 것. -->

<html>
  <head>
    <title><tiles:getAsString name="title"/></title>
  </head>
  <body>
        <table>
      <tr>
        <td colspan="2">
          <tiles:insertAttribute name="header" />
        </td>
      </tr>
      <tr>
        <td>
          <tiles:insertAttribute name="menu" />
        </td>
        <td>
          <tiles:insertAttribute name="body" />
        </td>
      </tr>
      <tr>
        <td colspan="2">
          <tiles:insertAttribute name="footer" />
        </td>
      </tr>
    </table>
  </body>
</html>