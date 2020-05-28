<?php

$host = "localhost";
$user = "id13826075_uv";
$password = "GQHtOIpQt*Ld6]>%";
$database = "id13826075_hotel";


$conn = mysqli_connect($host, $user, $password, $database);

if(isset($_GET['hab'])){

  $query = "SELECT luces,temperatura,llaves FROM Servicios WHERE habitacion = '$_GET[hab]'";
  $sql_statement = $conn->prepare($query);
  $sql_statement->execute();
  $result = $sql_statement->get_result();

  while ($row = $result->fetch_assoc()) {
    $servicio[] = array_map('utf8_encode',$row);
  }

  echo json_encode($servicio);


}else{
  header('Location:index.php');
}

?>
