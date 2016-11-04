<?php
/*
 * Created by PhpStorm.
 * User: JongYoon
 * Date: 2015-11-28
 * Time: 오후 2:22
 */

$list = isset($_SESSION['info_list']) ? $_SESSION['info_list'] : null;
?>

<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    <style type="text/css">

    </style>
    <script type="text/javascript">
        $(function () {
            $('#container').highcharts({
                chart: {
                    type: 'column'
                },
                title: {
                    text: '제품별 판매량'
                },
                subtitle: {

                },
                xAxis: {
                    categories: [
                        '판매량'
                    ],
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    max: 50,
                    title: {
                        text: 'Rainfall (개)'
                    }
                },
                tooltip: {
                    headerFormat: '<span style="font-size:5px">{point.key}</span><table>',
                    pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.f} 개</b></td></tr>',
                    footerFormat: '</table>',
                    shared: false,
                    useHTML: true
                },
                plotOptions: {
                    column: {
                        pointPadding: 0.4,
                        borderWidth: 0
                    }
                },
                series: [
                    <?php
                        for($index_i = 0 ; $index_i < count($list) ; $index_i++){
                            echo "{";
                            echo "name: '".$list[$index_i]['product']."', ";
                            echo "data: [".$list[$index_i]['sold']."]";
                            echo "}";
                            if($index_i != count($list)-1){
                            echo ", ";
                            }
                        }
                    ?>
]
            });
        });
    </script>
</head>
<body>
<script src="../js/highcharts.js"></script>
<script src="../js/exporting.js"></script>

<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
<br>
<span><a href="../controller/mainCtrl.php?func=300">리스트</a></span>
<a href="../controller/mainCtrl.php?func">메인 화면</a><br>
</body>
</html>
