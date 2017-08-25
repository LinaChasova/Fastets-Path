# Fastets-Path
Data structure: GRAPH

NB This problem is very similar to Fastest path problem. Be careful, the difference is highlighted. 

You are working in delivery company "LHD". Your company's first priority is speed of delivery. So, each time you are looking for the route for delivery, you select the fastest one. 
Now you are building the information system for customers, that estimates time and cost of delivery. Your database is undirected loopless graph with no parallel edges of roads which has information about distance (in km), time (in hours) and cost (in rubles/kilogram) of travelling between two points. This graph is stored in russia.txt file in the following format: 

russia.txt
VertexA VertexB VertextC
VertextA VertexB dist:time:cost VertextC VertexB dist:time:cost
For example: 

russia.txt
Бабяково Коньково Свиблово
Бабяково Коньково 120.0:4.3:100.50 Свиблово Коньково 220.0:4.0:10.0 Свиблово Бабяково 1000.0:400.0:1000.0
Means that there are 120.0 km between Бабяково and Коньково, time to travel is 4.3 hours, and one kilogram delivery will cost 100.5 rubles. 
Having this graph you can estimate delivery time and costs for you clients. You are given input.txt with requests in each line: 

input.txt
Бабяково Свиблово 10.0
Бабяково Коньково 5.0
Means clients are going to deliver 10 kilos from Бабяково to Свиблово and 5 kilos from Бабяково to Коньково. 

Today your company is upgrading the trucks, so you decided to minimize total routes length for the whole graph of roads. In other words, you decided to keep only minimal spanning tree of your logistic network in terms of distances. 

Your system should estimate time and costs for customer's requests, and put them into output.txt file like this: 

output.txt
Бабяково Свиблово 10.0 8.3 1105.0
Бабяково Коньково 5.0 4.3 502.5
E.g. first line means "from Бабяково to Свиблово 10.0 kilos will be delivered in 8.3 hours for (100.5+10.0)*10.0 = 1105 rubles". 
NB: All the number should be written with a single decimal digit after the point: 1234.5
