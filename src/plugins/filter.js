import moment from 'moment'

export function unformatMoney(value) {
  if (!value) return ''
  return parseFloat(value.replaceAll('.', '').replaceAll(',', '.'))
}

export function formatMoney(number) {
  if (!number) return ''
  return `R$ ${number.toFixed(2).replace('.', ',')}`
}

export function formatMoneyS(value) {
  if (!value) return 'R$ 0,00'
  return value.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL', minimumFractionDigits: 2, maximumFractionDigits: 3})
}

export function formatDate(date) {
  if (!date) return ''
  return moment(date).format('DD/MM/YYYY')
}

export function formatFullDate(date) {
  if (!date) return ''
  return moment(date).format('DD/MM/YYYY HH:mm')
}

export function formatHour(sec) {
  if (!sec) return ''
  else {
    let secInt = parseInt(sec, 10)
    let hours = Math.floor(secInt / 3600)
    let minutes = Math.floor((secInt - hours * 3600) / 60)
    let seconds = secInt - hours * 3600 - minutes * 60

    if (hours < 10) {
      hours = '0' + hours
    }
    if (minutes < 10) {
      minutes = '0' + minutes
    }
    if (seconds < 10) {
      seconds = '0' + seconds
    }

    return hours + ':' + minutes + ':' + seconds
  }
}

export function formatCNPJ(doc) {
  if (!doc) return ''
  if (doc && doc.length >= 13) {
    return doc.replace(/(\d{2})(\d{3})(\d{3})(\d{4})(\d+)/g, '$1.$2.$3/$4-$5')
  } else {
    return doc.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/g, '$1.$2.$3-$4')
  }
}

export function formatNCM(doc) {
  return doc.replace(/^(\d{4})(\d{2})(\d{2})$/, '$1.$2.$3')
}
