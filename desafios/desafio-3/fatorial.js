const readline = require('readline');
const rdInterface = readline.createInterface({
    input: process.stdin,
    output: process.stdout
  });

function calcFatorial(num) {
    return (num === 0 || num === 1) 
        ? 1 
        : num * calcFatorial(num - 1);
}

rdInterface.question('Informe um número para calcular o fatorial: ', (num) => {
  num = parseInt(num);

  if (isNaN(num)) {
    console.log('Digite um número válido!');
    rdInterface.close();
    return;
  }

  const resultado = calcFatorial(num);
  console.log(`O fatorial de ${num} é ${resultado}`);

  rdInterface.close();
});
