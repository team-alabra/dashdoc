using AutoMapper;
using Dashdoc.API.Domain.Entities;
// We use this alias to Change the name of classes
// For example, Provider entity and Provider model have the same name essentially,
// but when imported here we can alias the model as ProviderModel and the entity as
// just Provider
using ProviderModel = Dashdoc.API.Domain.Models.Provider;

namespace Dashdoc.API.Server.Mappings;

public class FromEntityProfile: Profile
{
    public FromEntityProfile()
    {
        // Insert all entity mappers below
        
        CreateMap<Provider, ProviderModel>()
            .ReverseMap(); // ReverseMap means we can map from entity to model and vice versa.
        
    }
}