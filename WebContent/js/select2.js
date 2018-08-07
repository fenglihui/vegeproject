$.fn.selectDate = function() {
    var minYear = 2012;
    var maxYear = (new Date).getFullYear() - 1;
    var yearSel = document.getElementById('year');

    for (var y = maxYear; y >= minYear; y--) {
        var yearOpt = document.createElement('option');
        yearOpt.value = y
        yearOpt.innerHTML = y + '年'
        yearSel.appendChild(yearOpt)
    }
}