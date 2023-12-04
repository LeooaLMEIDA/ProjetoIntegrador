<template>
  <div style="max-width: 100%;" class="chartComponent">
    <div ref="chart" style="height: 400px; min-width: 0;"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts';

export default {
  name: 'TheDoughnutChart',

  props: {
    data: Array,
  },

  mounted() {
    this.chart = echarts.init(this.$refs.chart)

    const container = document.querySelector('.chartComponent');

    new ResizeObserver(() => this.chart.resize()).observe(container);

    this.updateChart();
  },

  methods: {
    updateChart() {
      const option = {
        tooltip: { trigger: 'item' },
        legend: { top: 0, left: 'center' },
        series: [
          {
            name: 'Ordens de Serviço',
            type: 'pie',
            radius: ['30%', '60%'],
            label: {
              show: true,
              formatter(param) {
                return '{a|' + param.name + '}\n{b|' + param.value + '}'
              },
              rich: {
                b: {
                  fontWeight: 'bold'
                }
              }
            },
            data: this.data,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            },
          },
        ],
      }

      this.chart.setOption(option);
    },
  },

  watch: {
    data() {
      this.updateChart();
    }
  },

  beforeUnmount() {
    this.chart.dispose()
  },
}
</script>
