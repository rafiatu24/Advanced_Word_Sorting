<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sorting Algorithm Selector</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    <style>
        body {
            padding: 20px;
        }
        .container {
            max-width: 600px;
            margin: auto;
        }
        .result {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="text-center">Sorting Algorithm Application</h2>
        <form id="sortingForm">
            <div class="form-group">
                <label for="data">Enter Data (comma-separated):</label>
                <input type="text" class="form-control" id="data" name="data" placeholder="e.g. 5,3,8,1,4" required>
            </div>

            <div class="form-group">
                <label for="algorithm">Select Sorting Algorithm:</label>
                <select class="form-control" id="algorithm" name="algorithm" required>
                    <option value="heap">Heap Sort</option>
                    <option value="quick">Quick Sort</option>
                    <option value="merge">Merge Sort</option>
                    <option value="bucket">Bucket Sort</option>
                    <option value="radix">Radix Sort</option>

                </select>
            </div>

            <button type="submit" class="btn btn-primary btn-block">Sort</button>
        </form>

        <div id="result" class="result" style="display: none;">
            <h4>Sorted Data:</h4>
            <p id="sortedData"></p>
            <h5>Execution Time: <span id="executionTime"></span> ms</h5>
        </div>
    </div>

    <script>
        // Event handler for form submission
        $("#sortingForm").submit(function(event) {
            event.preventDefault();

            // Gather form data
            var data = $("#data").val().split(',').map(function(item) {
                return parseInt(item.trim());
            });
            var algorithm = $("#algorithm").val();

            // Prepare the JSON payload
            var requestData = {
                "data": data,
                "algorithm": algorithm
            };

            // Send the POST request to the sorting controller
            $.ajax({
                url: '/AdvancedWordSorting_war/sort/sorting',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(requestData),
                success: function(response) {
                    // Display the results
                    $("#sortedData").text(response.sortedData.join(', '));

                    console.log(response.sortedData);
                    $("#executionTime").text(response.executionTime);
                    $("#result").show();


                },
                error: function(xhr, status, error) {
                    alert("Error: " + error);
                }
            });
        });
    </script>
</body>
</html>
