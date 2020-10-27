/**
 * Created by PanJiaChen on 16/11/18.
 */

/**
 * Parse the time to string
 * @param {(Object|string|number)} time
 * @param {string} cFormat
 * @returns {string | null}
 */
export function parseTime(time, cFormat) {
  if (arguments.length === 0) {
    return null
  }
  const format = cFormat || '{y}-{m}-{d} {h}:{i}:{s}'
  let date
  if (typeof time === 'object') {
    date = time
  } else {
    if (typeof time === 'string' && /^[0-9]+$/.test(time)) {
      time = parseInt(time)
    }
    if (typeof time === 'number' && time.toString().length === 10) {
      time = time * 1000
    }
    date = new Date(time)
  }
  const formatObj = {
    y: date.getFullYear(),
    m: date.getMonth() + 1,
    d: date.getDate(),
    h: date.getHours(),
    i: date.getMinutes(),
    s: date.getSeconds(),
    a: date.getDay()
  }
  const time_str = format.replace(/{([ymdhisa])+}/g, (result, key) => {
    const value = formatObj[key]
    // Note: getDay() returns 0 on Sunday
    if (key === 'a') {
      return ['日', '一', '二', '三', '四', '五', '六'][value]
    }
    return value.toString().padStart(2, '0')
  })
  return time_str
}

/**
 * @param {number} time
 * @param {string} option
 * @returns {string}
 */
export function formatTime(time, option) {
  if (('' + time).length === 10) {
    time = parseInt(time) * 1000
  } else {
    time = +time
  }
  const d = new Date(time)
  const now = Date.now()

  const diff = (now - d) / 1000

  if (diff < 30) {
    return '刚刚'
  } else if (diff < 3600) {
    // less 1 hour
    return Math.ceil(diff / 60) + '分钟前'
  } else if (diff < 3600 * 24) {
    return Math.ceil(diff / 3600) + '小时前'
  } else if (diff < 3600 * 24 * 2) {
    return '1天前'
  }
  if (option) {
    return parseTime(time, option)
  } else {
    return (
      d.getMonth() +
      1 +
      '月' +
      d.getDate() +
      '日' +
      d.getHours() +
      '时' +
      d.getMinutes() +
      '分'
    )
  }
}

/**
 * @param {string} url
 * @returns {Object}
 */
export function param2Obj(url) {
  const search = url.split('?')[1]
  if (!search) {
    return {}
  }
  return JSON.parse(
    '{"' +
      decodeURIComponent(search)
        .replace(/"/g, '\\"')
        .replace(/&/g, '","')
        .replace(/=/g, '":"')
        .replace(/\+/g, ' ') +
      '"}'
  )
}

/**
 * This is just a simple version of deep copy
 * Has a lot of edge cases bug
 * If you want to use a perfect deep copy, use lodash's _.cloneDeep
 * @param {Object} source
 * @returns {Object}
 */
export function deepClone(source) {
  if (!source && typeof source !== 'object') {
    throw new Error('error arguments', 'deepClone')
  }
  const targetObj = source.constructor === Array ? [] : {}
  Object.keys(source).forEach(keys => {
    if (source[keys] && typeof source[keys] === 'object') {
      targetObj[keys] = deepClone(source[keys])
    } else {
      targetObj[keys] = source[keys]
    }
  })
  return targetObj
}

export function findArrayItem(
  list = [],
  key = 'id',
  value,
  childrenKey = 'children'
) {
  let match
  try {
    list = list || []
    list.forEach(item => {
      if (item[key] === value) {
        match = item
        throw new Error('stopForEach')
      } else if (item[childrenKey]) {
        match = findArrayItem(item[childrenKey], key, value, childrenKey)
        if (match) {
          throw new Error('stopForEach')
        }
      }
    })
  } catch (e) {
    if (e.message !== 'stopForEach') {
      throw e
    }
  }

  return match
}

export function delArrayItem(
  list = [],
  key = 'id',
  value,
  childrenKey = 'children'
) {
  let flag
  try {
    list = list || []
    list.forEach((item, index) => {
      if (item[key] === value) {
        list.splice(index, 1)
        flag = true
        throw new Error('stopForEach')
      } else if (item[childrenKey]) {
        flag = delArrayItem(item[childrenKey], key, value, childrenKey)
        if (flag) {
          throw new Error('stopForEach')
        }
      }
    })
  } catch (e) {
    if (e.message !== 'stopForEach') {
      throw e
    }
  }

  return flag
}

export function checkArray(
  list = [],
  key = 'id',
  childrenKey = 'children',
  checks = []
) {
  list = list || []
  list.forEach((item, index) => {
    if (checks.indexOf(item[key]) > -1) {
      item.checked = true
    }
    if (item[childrenKey]) {
      checkArray(item[childrenKey], key, childrenKey, checks)
    }
  })
}

export function setArrayItemValue(
  list = [],
  key = 'id',
  value = '',
  childrenKey = 'children',
  backupChildrenKey = 'btns'
) {
  list = list || []
  list.forEach((item, index) => {
    item[key] = value
    const children = item[childrenKey] || item[backupChildrenKey]
    if (children) {
      setArrayItemValue(children, key, value, childrenKey, backupChildrenKey)
    }
  })
}

export function removeArrayItemValue(
  list = [],
  key = 'id',
  childrenKey = 'children'
) {
  list = list || []
  list.forEach((item, index) => {
    delete item[key]
    if (item[childrenKey]) {
      checkArray(item[childrenKey], key, childrenKey)
    }
  })
}

export function renameArrayItem(
  list = [],
  oldName = 'id',
  newName = '',
  childrenKey = 'children'
) {
  list = list || []
  list.forEach((item, index) => {
    const children = item[childrenKey]
    item[newName] = item[oldName]
    delete item[oldName]
    if (children) {
      checkArray(children, oldName, newName, childrenKey)
    }
  })
}

export function filterArrayItem(
  list = [],
  filterKey = 'id',
  filterValue = '',
  childrenKey = 'children',
  backupChildrenKey = 'btns'
) {
  let result = []
  list = list || []
  list.forEach((item, index) => {
    if (item[filterKey] === filterValue) {
      result.push(item)
    }
    const children = item[childrenKey] || item[backupChildrenKey]
    if (children) {
      result = result.concat(
        filterArrayItem(
          children,
          filterKey,
          filterValue,
          childrenKey,
          backupChildrenKey
        )
      )
    }
  })
  return result
}

export function removeEmptyItem(obj = {}) {
  const result = {}
  for (const i in obj) {
    if (obj[i] !== '') {
      result[i] = obj[i]
    }
  }

  return result
}
