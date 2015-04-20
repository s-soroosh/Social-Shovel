def get_data(classification_mode=False):
    source = file("train_data.txt", "rb")
    # classification_mode if we youse classification or regression...
    data = []
    labels = []
    for l in source:
        entry = l.split("%%%")
        if len(entry) == 2:
            data.append(entry[0])
            label = int(entry[1].strip())
            if classification_mode:
                if label >= 50:
                    label = 1
                else:
                    label = 0
            labels.append(label)
    return data, labels
            
