package desafio.itau.springboot.dto;

import java.util.DoubleSummaryStatistics;

public class EstatisticaDTO {
    private long count;
    private double sum;
    private double avg;
    private double min;
    private double max;

    public EstatisticaDTO() {
    }

    public EstatisticaDTO(DoubleSummaryStatistics statistics) {
        this.count = statistics.getCount();
        this.sum = statistics.getSum();
        this.avg = statistics.getCount() > 0 ? statistics.getAverage() : 0.0;
        this.min = statistics.getCount() > 0 ? statistics.getMin() : 0.0;
        this.max = statistics.getCount() > 0 ? statistics.getMax() : 0.0;
    }

    public long getCount() {
        return count;
    }

    public double getSum() {
        return sum;
    }

    public double getAvg() {
        return avg;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}
