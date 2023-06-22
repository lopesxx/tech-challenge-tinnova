function bubbleSort(arr) {
    var i, j;
    var len = arr.length;
    var trocado = false;
  
    for (i = 0; i < len; i++) {
        trocado = false;
  
        for (j = 0; j < len; j++) {
            if (arr[j] > arr[j + 1]) {
                var temp = arr[j]
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
                trocado = true;
            }
        }
  
        if (!trocado) {
            break;
        }
    }
  
    console.log(arr)
}
  
var arr = [5, 3, 2, 4, 7, 1, 0, 6];
  
bubbleSort(arr)