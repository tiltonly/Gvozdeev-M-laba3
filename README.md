# lab10
## Задание: Доработайте приложение, добавив в списки фильтры, которые выводят либо всех студентов (уроки), либо только присутствующих, либо только отсутствующих.
1. Был создан enum class VisibilityFilter
<img src = https://sun9-44.userapi.com/c855732/v855732485/2394c2/hZpuAYAdtZU.jpg>

2. Далее в классе State добавили свойство visibilityFilter, которое показывает все предметы или всех студентов
<img src = https://sun9-51.userapi.com/c855732/v855732385/238d29/hyD9zKhU5FI.jpg>

3. В actions.kt добавили новое действие SetVisibilityFilter
<img src = https://sun9-62.userapi.com/c855732/v855732385/238d31/QQPhQE2268k.jpg>

4. Далее в reducers.kt была создана функция (действие) visibility
<img src = https://sun9-27.userapi.com/c855732/v855732385/238d38/bT8B_2uY1U0.jpg>

5. В filterContainer мы инициализируем state
<img src = https://sun9-3.userapi.com/c855732/v855732385/238d4a/-9funn-3LdM.jpg>

6. В anyFullContainer создаем функции для видимости студентов и предметов. Для показа всех студентов или предметов функция формирует исходный список студентов или предметов. Для проверки отсутсвующих или присутсвющих студентов на каком либо предмете, формируется новая карта по условиям отсутсвия или присутсвия. Для просмотра предметов на которых присутсвовал студент, происходят аналогичные действия, что и для студентов.
<img src = https://sun9-64.userapi.com/c855732/v855732385/238d52/y1g8215ErLk.jpg>
<img src = https://sun9-18.userapi.com/c855732/v855732385/238d5a/fkPxM4ZlfMA.jpg>

7. Далее в filter.kt создаем функцию вывода кнопок для нашего фильтра и вызываем ее в компоненте anyFull
<img src = https://sun9-31.userapi.com/c855732/v855732385/238d6a/6DD5GSPiy5A.jpg>

8. В компоненте Link создаем функцию рендера(визуализации) наших кнопок
<img src = https://sun9-8.userapi.com/c855732/v855732385/238d72/2j0UlWA9XK4.jpg>

9. Результаты работы приложения:

9.1. Работа фильтра для предметов

 - Все студенты для данного предмета

<img src = https://sun9-9.userapi.com/c855732/v855732385/238d83/ZM062mQ4OuU.jpg>

 - Отсутсвующие студенты на данном предмете
  
  <img src = https://sun9-56.userapi.com/c855732/v855732385/238d8a/7T2qlLeN78o.jpg>
  
  - Присутствующие студенты на данном предмете
  
    <img src = https://sun9-3.userapi.com/c855732/v855732385/238d91/CGCLYEcBfA4.jpg>
    
9.2. Работа фильтра для стундентов

- Все предметы для данного студента

<img src = https://sun9-13.userapi.com/c855732/v855732385/238daf/AdvURzCjjpw.jpg>

- Все предметы, на которых отсутствует данный студент

<img src = https://sun9-29.userapi.com/c855732/v855732385/238dca/nYg0T5dJrUw.jpg>

- Все предметы, на которых присутствует данный студент

<img src = https://sun9-30.userapi.com/c855732/v855732385/238dd1/mmZSVuJkPsk.jpg>
