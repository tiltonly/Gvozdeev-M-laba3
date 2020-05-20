# lab9
## Задание: Используя код приложения из лекций переделайте приложения из последнего задания предыдущего модуля с использованием redux. Реализовать хранилище нужно простым способом, без использования функций комбинирования reducer'ов
1. Для начала из состояния компонента app мы перенесли все элементы в класс State
<img src = https://sun9-63.userapi.com/c857328/v857328763/19ba20/1bkPoBBPsTE.jpg>

2. В actions добавляем новые классы
<img src = https://sun9-68.userapi.com/c857328/v857328763/19ba28/7FnqOKOjwf8.jpg>

3. В reducer прописываем все то, что будет происходить с состояниями при добавлении или удалении предметов и студентов
<img src = https://sun9-18.userapi.com/c857328/v857328544/19e1d5/p7TrSqX2h5o.jpg>
<img src = https://sun9-59.userapi.com/c857328/v857328544/19e1de/smJQfZrkJPg.jpg>

4. Изменения в компоненте app:

- пути для изменения студентов и предметов
<img src = https://sun9-28.userapi.com/c857328/v857328544/19e229/WMrave2i0JU.jpg>

- функции для добавления или удаления студентов и предметов (чтобы удалить или добавить предмет или студента мы находим элемент, который ввели, далее при помощи вызова функции dispatch вносим изменения в хранилище)
<img src = https://sun9-57.userapi.com/c857328/v857328544/19e23b/k6gspZeoH-4.jpg>

5. Результат работы приложения:

- приложение после запуска
<img src = https://sun9-47.userapi.com/c857328/v857328544/19e242/sH-ELlu4r7w.jpg>

- добавляем и удаляем студента
<img src = https://sun9-59.userapi.com/c857328/v857328544/19e249/qVYPTWbXXYw.jpg>
<img src = https://sun9-47.userapi.com/c857328/v857328544/19e259/9wOaIo6Hazs.jpg>

- добавляем и удаляем предмет
<img src = https://sun9-15.userapi.com/c857328/v857328544/19e260/j28nk4FTC_E.jpg>
<img src = https://sun9-8.userapi.com/c857328/v857328544/19e267/MmcOccRsUGw.jpg>
