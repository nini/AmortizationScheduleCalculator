<?php
// no headers, no final row
// no user input
// main calculation algorithm only
// hard-coded

$loan = 100000;
$debt = $loan;
$borrowingRate = 2.12;
$amortizationRate = 2.00;
$fixedInterestRate = 10;
$burden = round(($amortizationRate + $borrowingRate) * $debt / 12 / 100, 2);
$ann = round(($borrowingRate) * $debt / 12 / 100, 2);
$amm = $burden - $ann;


$date = new DateTime('2015-11-30');

print $date->format("d.m.Y"). "\t";
print formatNumber(-$debt) . "\t";
print formatNumber(0) ."\t\t";
print formatNumber(-$debt) . "\t";
print formatNumber(-$debt) . "\n";


$date = clone $date;
$date->add(new DateInterval('P1M'));

foreach (range(1, $fixedInterestRate * 12) as $month) {
    $debt -= $amm;

    print $date->format("t.m.Y\t");
    print formatNumber(-$debt) . " \t";
    print formatNumber($ann) . " \t";
    print formatNumber($amm) . " \t";
    print formatNumber($burden) . "\n";

    $ann = round($borrowingRate * $debt / 12 / 100, 2);
    $amm = $burden - $ann;

    $date = clone $date;
    $date->add(new DateInterval('P1M'));
}


function formatNumber($number) {
    return number_format($number, 2, ',', '.') . ' €';
}

?>