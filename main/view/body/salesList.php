<?php
/**
 * Created by PhpStorm.
 * User: JongYoon
 * Date: 2015-11-28
 * Time: 오후 2:22
 */

$list = isset($_SESSION['info_list']) ? $_SESSION['info_list'] : null;

?>

<body>
    <table>
        <th>순번</th>
        <th>상품명</th>
        <th>수량</th>
        <th>날짜</th>
        <?php
        for ($index_i = 0 ; $index_i < count($list) ; $index_i ++) {
            echo "<tr>";
                echo "<td>".($index_i+1)."</td>";
                echo "<td>".$list[$index_i]['product']."</td>";
                echo "<td>".$list[$index_i]['sold']."</td>";
                echo "<td>".$list[$index_i]['date']."</td>";
            echo "</tr>";
        }
        ?>
    </table>
    <br>
    <span><a href="../controller/mainCtrl.php?func=310">그래프</a></span>
    <a href="../controller/mainCtrl.php?func">메인 화면</a><br>
</form>
</body>